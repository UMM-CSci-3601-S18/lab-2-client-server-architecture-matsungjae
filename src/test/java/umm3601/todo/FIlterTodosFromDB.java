package umm3601.todo;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class FIlterTodosFromDB {
  @Test
  public void filterTodosByCategory() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] software_designTodos = db.filterTodosByCategory(allTodos, "software design");
    assertEquals("Incorrect category of software design", 74, software_designTodos.length);

    Todo[] video_gamesTodos = db.filterTodosByCategory(allTodos, "video games");
    assertEquals("Incorrect category of video games", 71, video_gamesTodos.length);
  }

  @Test
  public void filterTodosByBody() throws IOException {
    Database db = new Database("src/main/data/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] cillumTodos = db.filterTodosByBody(allTodos, "cillum");
    assertEquals("Incorrect number of todos containing 'cillum'", 72, cillumTodos.length);

    //adipisicing
    Todo[] adipisicingTodos = db.filterTodosByBody(allTodos, "adipisicing");
    assertEquals("Incorrect number of todos containing 'adipisicing'", 86, adipisicingTodos.length);

  }


}
