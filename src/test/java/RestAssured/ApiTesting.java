package RestAssured;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiTesting {
	@Test(enabled = false)
	public void setup() {
		Response rep = RestAssured.get("https://api.github.com");
		int statusCode = rep.statusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test(enabled = false)
	public void CreateRepo() {
		JSONObject obj = new JSONObject();
		obj.put("name", "Dipali1");
		obj.put("description", "Tester");
		obj.put("homepage", "https://github.com");
		obj.put("private", false);
		obj.put("is_template", true);
//		given().body(obj.toJSONString()).when().post("https://api.github.com/user/repos").then().statusCode(201).log()
//				.all();
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.body(obj.toJSONString()).when().post("https://api.github.com/user/repos").then().statusCode(201).log()
				.all();

	}

	@Test(enabled = false)
	public void UpdateRepo() {
		JSONObject obj = new JSONObject();
		obj.put("name", "Hello");
		obj.put("description", "This is my first Repo");
		obj.put("homepage", "https://github.com");
		obj.put("private", true);
		obj.put("has_issues", true);
		obj.put("has_projects", true);
		obj.put("has_wiki", true);

		given().auth().oauth2("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").header("Content-Type", "application/json")
				.body(obj.toJSONString()).when().patch("https://api.github.com/repos/dips2022/SDET-Project").then()
				.statusCode(200).log().all();

	}

	@Test(enabled = false)
	public void GetRepo() {
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.given().get(" https://api.github.com/repos/dips2022/SDET-Project").then().statusCode(200).log().all();
	}
	// id 741003425

	@Test(enabled = false)
	public void DeleteRepo() {
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.given().delete("https://api.github.com/repos/dips2022/Hello").then().statusCode(204).log().all();

	}

	@Test(enabled = false)
	public void CreateFork() {
		JSONObject obj = new JSONObject();

		obj.put("name", "masai_repo");
		obj.put("default_branch_only", true);

		given().auth().oauth2("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").header("Content-Type", "application/json")
				.body(obj.toJSONString()).when()
				.post("https://api.github.com/repos/masai-course/gitCollaboration/forks").then().statusCode(202).log()
				.all();

	}

	@Test(enabled = false)
	public void ListFork() {
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.given().get("https://api.github.com/repos/masai-course/gitCollaboration/forks").then().statusCode(200)
				.log().all();

	}

	@Test(enabled = false)
	public void ListRepo() {
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.given().get(" https://api.github.com/users/dips2022/repos").then().statusCode(200).log().all();
	}

	@Test(enabled = false)
	public void ListRepoLang() {
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.given().get("https://api.github.com/repos/dips2022/nop_commerce/languages").then().statusCode(200)
				.log().all();

	}

	@Test(enabled = false)
	public void ListPublicRepo() {
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.given().get("https://api.github.com/repositories").then().statusCode(200).log().all();

	}

	@Test(enabled = false)
	public void Create_Or_Update() {
		JSONObject obj = new JSONObject();
		obj.put("name", "dipali");
		obj.put("email", "dipalinsonawane1999@gmail.com");

		JSONObject obj1 = new JSONObject();
		obj1.put("message", "my first commit");

		obj1.put("committer", obj);
		obj1.put("content", "bXkgbmV3IGZpbGUgY29udGVudHM=");

		given().auth().oauth2("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").header("Content-Type", "application/json")
				.body(obj1.toJSONString()).when()
				.put("https://api.github.com/repos/dips2022/nop_commerce/contents/hello.txt").then().statusCode(201)
				.log().all();

	}

	@Test(enabled = false)
	public void Delete_File() {

		JSONObject obj1 = new JSONObject();

		obj1.put("name", "dipali");
		obj1.put("email", "dipalinsonawane1999@gmail.com");

		JSONObject obj = new JSONObject();

		obj.put("message", "deleted file");
		obj.put("committer", obj1);
		obj.put("sha", "0d5a690c8fad5e605a6e8766295d9d459d65de42");

		given().auth().oauth2("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").header("Content-Type", "application/json")
				.body(obj.toJSONString()).when()
				.delete("https://api.github.com/repos/dips2022/nop_commerce/contents/hello.txt").then().statusCode(200)
				.log().all();

	}

	@Test(enabled = false)
	public void List_Repo_Tags() {
		given().auth().oauth2("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").header("Content-Type", "application/json")
				.given().get(" https://api.github.com/repos/dips2022/nop_commerce/tags").then().statusCode(200).log()
				.all();
	}

	@Test(enabled = false)
	public void Create_An_Autolink() {

		JSONObject obj1 = new JSONObject();

		obj1.put("key_prefix", "TICKET-");
		obj1.put("url_template", "https://example.com/TICKET?query=<num>");
		obj1.put("is_alphanumeric", true);

		given().auth().oauth2("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").header("Content-Type", "application/json")
				.body(obj1.toJSONString()).when().post(" https://api.github.com/repos/dips2022/nop_commerce/autolinks")
				.then().statusCode(201).log().all();
	}
	@Test(enabled = false)
	public void Get_All_Repo_Topics() {
		given().auth().oauth2("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").header("Content-Type", "application/json").
        given().get("https://api.github.com/repos/dips2022/nop_commerce/topics").then().statusCode(200).log().all();
	}
	@Test(enabled = false)
	public void Get_All_AutoLink() {
		given().auth().oauth2("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").header("Content-Type", "application/json").
        given().get("https://api.github.com/repos/dips2022/nop_commerce/autolinks").then().statusCode(200).log().all();
	} 
	@Test(enabled = false)
	public void Delete_AutoLink() {
		given().auth().oauth2("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").header("Content-Type", "application/json").
         given().delete("https://api.github.com/repos/dips2022/nop_commerce/autolinks/3501056").then().statusCode(204).log().all();
 	}
	@Test(enabled = false)
	public void Get_Repo() {
		given().auth().oauth2("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").header("Content-Type", "application/json").
        given().get("https://api.github.com/repos/dips2022/nop_commerce").then().statusCode(200).log().all();
	}
	@Test(enabled = true)
	public void Replace_Repo_Topics() {
		
		JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray()	;
        arr.add("octocat");
        arr.add("atom");
        arr.add("electron");
        arr.add("api");
        
        obj.put("names", arr);
		given().auth().oauth2("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").header("Content-Type", "application/json").
        body(obj.toJSONString()).when().put("https://api.github.com/repos/dips2022/nop_commerce/topics").then().statusCode(200).log().all();
	}
}
