<script>
  import fastapi from "../lib/api";
  import { link } from "svelte-spa-router";

  let post_list = [];

  function get_post_list() {
    fastapi("get", "/forum/post/list", {}, (json) => {
      post_list = json;
    });
  }

  get_post_list();
</script>

<div class="container my-3">
  <table class="table">
    <thead>
      <tr class="table-dark">
        <th>#</th>
        <th>Title</th>
        <th>Date</th>
      </tr>
    </thead>
    <tbody>
      {#each post_list as post, i}
        <tr>
          <td>{i + 1}</td>
          <td>
            <a use:link href="/detail/{post.id}">{post.subject}</a>
          </td>
          <td>{post.create_date}</td>
        </tr>
      {/each}
    </tbody>
  </table>
</div>
