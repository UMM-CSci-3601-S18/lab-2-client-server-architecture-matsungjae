package umm3601.todo;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Database {
  private Todo[] allTodos;

  public Database(String userDataFile) throws IOException {
    Gson gson = new Gson();
    FileReader reader = new FileReader(userDataFile);
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public Todo[] listTodos() {

    return allTodos;
  }

  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  public Todo[] listTodos(Map<String, String[]> queryParams) {
    Todo[] filteredTodos = allTodos;

    // Filter category if defined
    if(queryParams.containsKey("category")) {
      String targetCategory = queryParams.get("category")[0];
      filteredTodos = filterTodosByCategory(filteredTodos, targetCategory);
    }
    // Filter by a string contained in the body
    if(queryParams.containsKey("body")) {
      String targetString = queryParams.get("body")[0];
      filteredTodos = filterTodosByBody(filteredTodos, targetString);
    }

    return filteredTodos;
  }

  public Todo[] filterTodosByCategory(Todo[] Todos, String targetCategory) {
    return Arrays.stream(Todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody(Todo[] todos, String targetString) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetString)).toArray(Todo[]::new);
  }
}
