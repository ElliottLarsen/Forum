<script>
  import fastapi from "../lib/api";
  import Error from "../components/Error.svelte";

  export let params = {};
  let post_id = params.post_id;
  console.log("post_id:" + post_id);
  let post = { comments: [] };
  let content = "";
  let error = { detail: [] };

  function get_post() {
    fastapi("get", "/forum/post/detail/" + post_id, {}, (json) => {
      post = json;
    });
  }

  get_post();

  function post_comment(event) {
    event.preventDefault();
    let url = "/forum/comment/create/" + post_id;
    let params = {
      content: content,
    };
    fastapi(
      "post",
      url,
      params,
      (json) => {
        content = "";
        error = { detail: [] };
        get_post();
      },
      (err_json) => {
        error = err_json;
      }
    );
  }
</script>

<div class="container my-3">
  <h2 class="border-bottom py-2">{post.subject}</h2>
  <div class="card my-3">
    <div class="card-body">
      <div class="card-text" style="white-space: pre-line;">{post.content}</div>
      <div class="d-flex justify-content-end">
        <div class="badge bg-light text-dark p-2">
          {post.create_date}
        </div>
      </div>
    </div>
  </div>
  <h5 class="border-bottom my-3 py-2">
    There are {post.comments.length} comments.
  </h5>
  {#each post.comments as comment}
    <div class="card my-3">
      <div class="card-body">
        <div class="card-text" style="white-space: pre-line;">
          {comment.content}
        </div>
        <div class="d-flex justify-content-end">
          <div class="badge bg-light text-dark p-2">
            {comment.create_date}
          </div>
        </div>
      </div>
    </div>
  {/each}
  <Error {error} />
  <form method="post" class="my-3">
    <div class="mb-3">
      <textarea rows="10" bind:value={content} class="form-control" />
    </div>
    <input
      type="submit"
      value="Comment"
      class="btn btn-primary"
      on:click={post_comment}
    />
  </form>
</div>
