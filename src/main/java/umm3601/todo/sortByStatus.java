package umm3601.todo;

import java.util.Comparator;

public class sortByStatus implements Comparator<Todo> {

  @Override
  public int compare(Todo a, Todo b) {
    Boolean first = a.status;
    Boolean second = b.status;
    return first.compareTo(second);
  }
}
