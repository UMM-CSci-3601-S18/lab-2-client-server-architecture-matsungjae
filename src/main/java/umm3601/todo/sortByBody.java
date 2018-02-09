package umm3601.todo;

import java.util.Comparator;

public class sortByBody implements Comparator<Todo> {

  @Override
  public int compare(Todo a, Todo b) {
    return a.body.compareTo(b.body);
  }
}
