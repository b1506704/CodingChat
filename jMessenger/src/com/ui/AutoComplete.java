/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author TsundereMoe
 */
public class AutoComplete implements DocumentListener{
    //enum to check state of task 
    private static enum Mode {
    INSERT,
    COMPLETION
  };

  private final JTextField textField;
  private final ArrayList<String> keywords;
  private Mode mode = Mode.INSERT;

  public AutoComplete(JTextField textField, ArrayList<String> keywords) {
    this.textField = textField;
    this.keywords = keywords;
    //for binary search later
    Collections.sort(keywords);
  }

  @Override
  public void changedUpdate(DocumentEvent ev) { }

  @Override
  public void removeUpdate(DocumentEvent ev) { }

  @Override
  public void insertUpdate(DocumentEvent ev) {
    //DocumentEvent==> emlements inserted? Removed?
    
    if (ev.getLength() != 1)
      return;
    
    int pos = ev.getOffset();
    //for storing string
    String content = null;
    try {
      //store up to the changed charater position
      content = textField.getText(0, pos + 1);
    } catch (BadLocationException e) {
        
    }
 
    // Find where the word starts
    int w;
    for (w = pos; w >= 0; w--) {
      //check if there is a space or non-letter in the typed word so far
      if (!Character.isLetter(content.charAt(w))) {
        break;
      }
    }

    // Only turn auto complete when type more than 2 characters
    if (pos - w < 2)
      return;
    //Store the prefix string (from start position -1+1=0)
    String prefix = content.substring(w + 1).toLowerCase();
    //index of prefix in the list provided return -
    int n = Collections.binarySearch(keywords, prefix); //n=-2
    //if not outof list
    if (n < 0 && -n <= keywords.size()) {
      //get insert point of keyword
      String match = keywords.get(-n - 1);
      //if keyword.element.prefix match with prefix
      if (match.startsWith(prefix)) {
        // A completion is found
        String completion = match.substring(pos - w);
        //  ==>submit a task that does the change later
        SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
      }
    } else {
      // Nothing found
      mode = Mode.INSERT;
    }
  }

  public class CommitAction extends AbstractAction {
    /**
     * 
     */
    //==> to avoid InvalidClassException due to different version every compile time
    // constant ID for class
    private static final long serialVersionUID = 5794543109646743416L;

    @Override
    public void actionPerformed(ActionEvent ev) {
      //insert a space and continue typing 
      if (mode == Mode.COMPLETION) {
        int pos = textField.getSelectionEnd();
        StringBuilder sb = new StringBuilder(textField.getText());
        sb.insert(pos, " ");
        textField.setText(sb.toString());
        textField.setCaretPosition(pos + 1);
        mode = Mode.INSERT;
      } else {
        //insert a tab if still typing
        textField.replaceSelection("\t");
      }
    }
  }
  //class that can run without initialize
  //merge prefix with completion(subfix)
  private class CompletionTask implements Runnable {
    private final String completion;
    private final int position;
 
    CompletionTask(String completion, int position) {
      this.completion = completion;
      this.position = position;
    }
 
    @Override
    public void run() {
      StringBuilder sb = new StringBuilder(textField.getText());
      sb.insert(position, completion);
      textField.setText(sb.toString());
      //define mark position
      textField.setCaretPosition(position + completion.length());
      //move the caret to the end of text and mark the whole text
      textField.moveCaretPosition(position);
      //=> change state of task to perform commit action
      mode = Mode.COMPLETION;
    }
  }
    
}
