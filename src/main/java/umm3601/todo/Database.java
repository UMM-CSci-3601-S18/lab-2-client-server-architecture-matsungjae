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
    //Filter by owner
    if(queryParams.containsKey("owner")) {
      String targetOwner = queryParams.get("owner")[0];
      filteredTodos = filterTodosByOwner(filteredTodos, targetOwner);
    }

    // limit the number of items displayed
    if(queryParams.containsKey("limit")) {
      int limit = Integer.parseInt(queryParams.get("limit")[0]);
      filteredTodos = limitTodos(filteredTodos, limit);
    }

    if(queryParams.containsKey("status")){
      boolean status = Boolean.parseBoolean(queryParams.get("status")[0]);
      filteredTodos = filterTodosByStatus(filteredTodos, status);
    }


    return filteredTodos;
  }

  public Todo[] filterTodosByCategory(Todo[] Todos, String targetCategory) {
    return Arrays.stream(Todos).filter(x -> x.category.equals(targetCategory)).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByBody(Todo[] todos, String targetString) {
    return Arrays.stream(todos).filter(x -> x.body.contains(targetString)).toArray(Todo[]::new);
  }

  public Todo[] limitTodos(Todo[] todos, int limit) {
    Todo[] filteredTodos = new Todo[limit];
    for (int i = 0; i < limit; i++) {
      filteredTodos[i] = todos[i];
    }
    return filteredTodos;
  }

  public Todo[] filterTodosByStatus(Todo[] todos, boolean status) {
    return Arrays.stream(todos).filter(x -> x.status == status).toArray(Todo[]::new);
  }


  public Todo[] filterTodosByOwner(Todo[] todos, String targetOwner) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(targetOwner)).toArray(Todo[]::new);
  }
}
