package umm3601.todo;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class FilterTodosUsingHashMap {
  Database db;
  Map<String, String[]> queryParams;

  @Before
  public void setup() throws IOException{
    db = new Database("src/main/data/todos.json");
    queryParams = new HashMap<>();
  }

  @Test
  public void listTodosWithCategoryFilter() {

    queryParams.put("category", new String[] {"software design"});
    Todo[] software_designTodos = db.listTodos(queryParams);
    assertEquals("Incorrect category of software design", 74, software_designTodos.length);

    queryParams.put("category", new String[] {"video games"});
    Todo[] video_gamesTodos = db.listTodos(queryParams);
    assertEquals("Incorrect category of video games", 71, video_gamesTodos.length);
  }

  @Test
  public void listTodosWithOwnerFilter() {

    queryParams.put("owner", new String[] {"Blanche"});
    Todo[] blancheTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number owned by Blanche", 43, blancheTodos.length);

  }

  @Test
  public void listTodosWithBodyFilter() {

    queryParams.put("body", new String[] {"cillum"});
    Todo[] cillumTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number containing cillum", 72, cillumTodos.length);

  }

  @Test
  public void listTodosWithStatusFilter() {

    queryParams.put("status", new String[] {"complete"});
    Todo[] completeTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number complete", 143, completeTodos.length);

    queryParams.put("status", new String[] {"incomplete"});
    Todo[] incompleteTodos = db.listTodos(queryParams);
    assertEquals("Incorrect number incomplete", 157, incompleteTodos.length);

  }

  @Test
  public void listTodosWithLimitFilter() {

    queryParams.put("limit", new String[] {"10"});
    Todo[] only10Todos = db.listTodos(queryParams);
    assertEquals("Incorrect number of todos", 10, only10Todos.length);

  }


}
