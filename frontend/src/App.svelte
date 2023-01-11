<script>
  let greeting;

  fetch("http://localhost:8000/").then((response) => {
    response.json().then((json) => {
      greeting = json.greeting;
    });
  });
</script>

<h1>{greeting}</h1>