package umm3601.todo;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class FIlterTodosFromDB {

  Database db;
  Todo[] allTodos;

  @Before
  public void setup() throws IOException {
    db = new Database("src/main/data/todos.json");
    allTodos = db.listTodos(new HashMap<>());
  }

  @Test
  public void filterTodosByCategory() {

    Todo[] software_designTodos = db.filterTodosByCategory(allTodos, "software design");
    assertEquals("Incorrect category of software design", 74, software_designTodos.length);

    Todo[] video_gamesTodos = db.filterTodosByCategory(allTodos, "video games");
    assertEquals("Incorrect category of video games", 71, video_gamesTodos.length);
  }

  @Test
  public void filterTodosByBody() {

    Todo[] cillumTodos = db.filterTodosByBody(allTodos, "cillum");
    assertEquals("Incorrect number of todos containing 'cillum'", 72, cillumTodos.length);

    //adipisicing
    Todo[] adipisicingTodos = db.filterTodosByBody(allTodos, "adipisicing");
    assertEquals("Incorrect number of todos containing 'adipisicing'", 86, adipisicingTodos.length);

  }

  @Test
  public void limitNumberOfTodos() {

    Todo[] only7Todos = db.limitTodos(allTodos, 7);
    assertEquals("Incorrect number of todos", 7, only7Todos.length);

    Todo[] only50Todos = db.limitTodos(allTodos, 50);
    assertEquals("Incorrect number of todos", 50, only50Todos.length);
  }

  @Test
  public void filterTodosByOwner() {
    Todo[] blancheTodos = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals("Incorrect number of Todos owned by Blanche",43, blancheTodos.length);

    for (Todo todo : blancheTodos) {
      assertEquals("Incorrect Owner", "Blanche", todo.owner);
    }
  }

  @Test
  public void setStatusOfTodos(){
    Todo[] statusCompleteTodos = db.filterTodosByStatus(allTodos, true);
    assertEquals("Incorrect status of todos", 143, statusCompleteTodos.length);

    Todo[] statusIncompleteTodos = db.filterTodosByStatus(allTodos, false);
    assertEquals("Incorrect status of todos", 157, statusIncompleteTodos.length);
  }



}
