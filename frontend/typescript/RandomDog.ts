document.addEventListener("DOMContentLoaded", function () {
  function getRandomDogImage() {
    const dogButton: HTMLButtonElement | null =
      document.querySelector<HTMLButtonElement>("#get-dog-btn");
    if (!dogButton) {
      console.error("Button with id 'get-dog-btn' not found.");
      return;
    }

    dogButton.addEventListener("click", function () {
      let imageUrl: string = "https://dog.ceo/api/breeds/image/random";
      const imgElement = document.querySelector<HTMLImageElement>("#dog-image");
      if (!imgElement) {
        console.error("Image element with id 'dog-image' not found.");
        return;
      }
      fetch(imageUrl)
        .then((response) => response.json())
        .then((data) => {
          imgElement.src = data.message;
        })
        .catch((error) => {
          console.error("Error fetching dog image:", error);
        });
    });
  }
  getRandomDogImage();
});
