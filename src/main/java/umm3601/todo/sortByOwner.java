package umm3601.todo;

import java.util.Comparator;

public class sortByOwner implements Comparator<Todo> {

  @Override
  public int compare(Todo a, Todo b) {
    return a.owner.compareTo(b.owner);
  }
}
