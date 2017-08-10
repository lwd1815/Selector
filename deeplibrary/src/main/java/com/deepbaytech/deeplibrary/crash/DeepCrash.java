package com.deepbaytech.deeplibrary.crash;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by EtherealPatrick on 2017/5/20.
 */

public final class DeepCrash {
  public interface ExceptionHandler{
    void handlerException(Thread thread,Throwable throwable);

  }

  public DeepCrash() {
  }

  private static ExceptionHandler exceptionHandler;
  private static Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
  private static boolean isInstalled;
  public static synchronized void install(ExceptionHandler eh){
    if (isInstalled) return;
    isInstalled = true;
    exceptionHandler = eh;
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override public void run() {
        while (true){
          try {
            Looper.loop();
          }catch (Throwable e){
            if (e instanceof DeepQuitException){
              return;
            }
            if (exceptionHandler != null){
              exceptionHandler.handlerException(Looper.getMainLooper().getThread(),e);
            }

          }
        }
      }
    });

    uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
      @Override public void uncaughtException(Thread thread, Throwable ex) {
        if (exceptionHandler != null){
          exceptionHandler.handlerException(thread,ex);
        }
      }
    });
  }

  public static synchronized void uninstall(){
    if (!isInstalled) return;
    isInstalled = false;
    exceptionHandler = null;
    Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override public void run() {
        throw new DeepQuitException("deep crash quit...");
      }
    });
  }
}
