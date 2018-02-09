package umm3601.todo;

import umm3601.todo.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class sortByCategory implements Comparator<Todo>{

  @Override
  public int compare(Todo a, Todo b) {
    return a.category.compareTo(b.category);
  }
}
