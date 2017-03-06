//  Project:                  Code/Java/101/Projects/4x4
//  File:                     JavaPipe.java
//  Created by:               David Casperson
//  Created:                  ???
//
//  Description:              Pipe class for communicating with Java subprocesses.

package ca.unbc.cpsc101;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Arrays;


/**
 * The JavaPipe provides a means to start a separate java program
 * and communicate with its System.in and System.out using Readers
 * and Writers.
 *
 * The JavaPipe class uses the {@link java.lang.Process Process} and
 * {@link java.lang.ProcessBuilder ProcessBuilder} classes to create
 * a process that runs on the other side of the JavaPipe.
 */
public class JavaPipe
{
  /**
   *  describes the version of the JavaPipe class.
   *  @since 2.0
   */
  public static final String version = "JavaPipe 2.0 (dgc)" ;


  /**
   *  The default PATH used to search for the java executable.  Unless
   *  {@link #setExecPath setExecPath} is called before {@link #start
   *  start}, this is where the 
   *  java executable is searched for.
   *  
   *  @see ca.unbc.cpsc101.JavaPipe#setExecPath setExecPath.
   */
  public static final String DEFAULT_EXEC_PATH = "/usr/bin:/usr/local/bin" ;


  /**
   *  @return whether or not a Process has been started
   */
  private boolean isRunning () { return (myProcess instanceof Process) ; }
  private void checkRunning () { assert isRunning() ; }
  private void checkStopped () { assert !isRunning() ; }


  /**
   *  returns the CLASSPATH that is inserted in the environment when
   *  starting java for this JavaPipe.  This influences where the java
   *  runtime looks for its .class argument.
   *
   *  @return the CLASSPATH variable to be used by this Pipe.
   */
  public String getClassPath() { return myClassPath ; }



  /**
   *  set the CLASSPATH environment variable to be used when
   *  starting the java process for this JavaPipe.
   *
   *
   *
   *  @param cp the value to which to set the CLASSPATH.
   *
   *  @return the {@code JavaPipe} that called this object.
   *  (allows for &ldquo;chained&rdquo; calls like
   *  {@code fred.setClassPath(".:..").start()}.
   */
  public JavaPipe setClassPath(String cp)
      {
       checkStopped() ;
       myClassPath = cp ;
       return this ;
      }



  /**
   *  This is the path that is used to locate the {@code java}
   *  executable.  When {@link #start the start method} is executed,
   *  JavaPipe attempts to start another java session remotely over a
   *  pipe using the path specified by getExecPath.
   *
   *  @return the path used to find the java command.
   */
  public String getExecPath() { return exec_path ; }



  /**
   *  Set the path used to find the {@code java} executable.
   *  When the {@link #start start} method is executed,
   *  JavaPipe attempts to start another java session remotely over a
   *  pipe using the path last path specified by setExecPath.
   *  The default is {@link #DEFAULT_EXEC_PATH DEFAULT_EXEC_PATH}.
   *
   *
   *  @param ep the value to which to set the PATH on which {@code
   *    java} will be found.
   *
   *  @return the {@code JavaPipe} that called this object.
   *  (allows for &ldquo;chained&rdquo; calls like
   *  {@code fred.setExecPath(".:..").start()}.
   */
  public JavaPipe setExecPath(String ep)
      {
       checkStopped() ;
       exec_path = ep ;
       return this ;
      }




  /**
   *  Sets up, but does not start a JavaPipe.
   *  <p>
   *  Between the creation of the JavaPipe and when it is started,
   *  .{@link #setExecPath setExecPath}() can be used to change the
   *  path where the java
   *  command is found, and .{@link #setClassPath setClassPath}(...)
   *  can be used to set
   *  the CLASSPATH variable used by java to find the requested
   *  class.
   *  </p>
   *
   *  <p>
   *  Note that the constructor does <em>not</em> begin execution of
   *  java, and does <em>not</em> check the legitimacy of the {@code
   *  className} argument.
   *  </p>
   *
   *  @param className  specifies the name of
   *  the class whose main is to be run to create the pipe.
   *
   *  @see #start start
   *
   */
  public JavaPipe(String className)
      {
       String [] myArgs = {"java", className} ;
       myProcessBuilder = new ProcessBuilder(Arrays.asList(myArgs)) ;
       myClassPath = myProcessBuilder.environment().get("CLASSPATH") ;
       exec_path = DEFAULT_EXEC_PATH ;
       myProcessBuilder.environment().clear();
       myProcessBuilder.directory(new java.io.File(".")) ;
//      TODO set the CLASSPATH correctly here
      }

  /**
   *  creates a java process and connects pipes for communication with
   *  it.
   *
   *  The associated process reads from System.in what this JavaPipe
   *  writes on this.{@link #getOutputWriter getOutputWriter}(), and what
   *  it prints on System.out 
   *  appears on this JavaPipe&rsquo;s this.{@link #getInputReader getInputReader}().
   *
   *  @return the {@code JavaPipe} that called this object.
   *  (allows for &ldquo;chained&rdquo; calls like
   *  {@code fred..start().getInputReader()}.
   *
   *  @throws java.io.IOException when the  {@link
   *  java.lang.ProcessBuilder#start start} method of the underlying {@link
   *  java.lang.ProcessBuilder ProcessBuilder} does.
   *
   *  @throws java.lang.IllegalThreadStateException when this method
   *  is able to detect early failure of the {@code java} program
   *  (perhaps due to being unable to find the specified class). <em>
   *  This is not guaranteed to work.</em>  Some errors may not be
   *  detected until attempting perform i/o with the JavaPipe.
   */
  public JavaPipe start() throws java.io.IOException
      {
       if (myProcess!=null)
           destroy() ;
       if (myClassPath!=null)
           myProcessBuilder.environment().put("CLASSPATH",myClassPath) ;
       else
           myProcessBuilder.environment().remove("CLASSPATH") ;
       myProcessBuilder.environment().put("PATH", exec_path) ;
       myProcess = myProcessBuilder.start() ;
       // check for immediate errors
       int return_status = -1 ;
       try
           {
           Thread.currentThread().sleep(300) ;
           return_status = myProcess.exitValue() ;
           }
       catch (InterruptedException e) {} // strange, but we'll ignore
       catch (IllegalThreadStateException x) {} // expected!
       if (return_status>=0)
           {
           throw new IllegalStateException("Process quit immediately.") ;
           }

       myProcessBuilder.redirectErrorStream(true) ;
       myReader = new java.io.InputStreamReader(myProcess.getInputStream()) ;
       myWriter = new java.io.PrintWriter(myProcess.getOutputStream(),true) ;
       return this ;
      }

  /**
   * This method provides an explicit means by which to release the
   * resources acquired by a JavaPipe.  It first closes the pipe
   * connections to the process, and then kills the process itself.
   *
   *
   *  @return the {@code JavaPipe} that called this object.
   *  (allows for &ldquo;chained&rdquo; calls like
   *  {@code fred.destroy().start()}.
   */
  public JavaPipe destroy()
      {
       try
           {
           if (myWriter instanceof Closeable) myWriter.close() ;
           if (myReader instanceof Closeable) myReader.close() ;
           if (myProcess instanceof Process)
               {
               myProcess.destroy();
               myProcess.waitFor();
               }
           }
       catch (java.io.IOException io)             {}
       catch (java.lang.InterruptedException ie)  {}
       finally
           {
           myProcess = null ;
           myWriter = null ;
           myReader = null ;
           }
       return this ;
      }


  /**
   *  This Reader provides the text that the corresponding process
   *  writes on System.out.
   *
   *  valid between the time the JavaPipe is {@code .start()}ed and when it is
   *  {@code .destroy()}ed, provided that the corresponding process is well
   *  behaved and does not quit.
   *
   *  @return a Reader that is connected to the output of the process
   *  on the other side of the pipe.
   */
  public Reader getInputReader()
      {
       return myReader ;
      }

  /**
   *  This Writer provides the text written to it to the corresponding
   *  processes&rsquo; System.in.
   *
   *  valid between the time the JavaPipe is {@code .start()}ed and when it is
   *  {@code .destroy()}ed, provided that the corresponding process is well
   *  behaved and doesn't quit.
   *
   *  @return a Writer that is connected to the input of the process
   *  on the other side of the pipe.
   */
  public PrintWriter getOutputWriter()
      {
       return myWriter ;
      }


  private String exec_path = null ;

  private ProcessBuilder myProcessBuilder ;
  private String myClassPath ;
  private Process myProcess ;
  private Reader myReader ;
  private PrintWriter myWriter ;
}
