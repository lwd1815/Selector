package com.deepbaytech.deeplibrary.crash;

/**
 * Created by EtherealPatrick on 2017/5/20.
 */

public class DeepQuitException extends RuntimeException {
  /**
   * Constructs a new {@code RuntimeException} that includes the current stack
   * trace.
   */
  public DeepQuitException(String msg) {
    super(msg);
  }
}
