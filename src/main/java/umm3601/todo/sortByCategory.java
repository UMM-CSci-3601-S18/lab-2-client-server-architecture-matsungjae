package umm3601.todo;
import java.util.*;
import java.lang.*;

public class sortByCategory implements Comparator<Todo>{

  @Override
  public int compare(Todo a, Todo b) {
    return a.category.compareTo(b.category);
  }
}
