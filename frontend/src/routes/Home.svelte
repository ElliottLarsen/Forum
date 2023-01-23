<script>
    let post_list = []
  
    function get_post_list() {
      fetch("http://127.0.0.1:8000/forum/post/list").then((response) => {
        response.json().then((json) => {
          post_list = json
        })
      })
    }
  
    get_post_list()
  </script>
  
  <ul>
    {#each post_list as post}
      <li>{post.subject}</li>
    {/each}
  </ul>