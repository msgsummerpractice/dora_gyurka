function getRandomDogImage() {

    var dogButton = document.getElementById("get-dog-btn").addEventListener("click", function() {

        const imageUrl = "https://dog.ceo/api/breeds/image/random";
        const imgElement = document.getElementById("dog-image");
        fetch(imageUrl)
            .then(response => response.json())
            .then(data => {
                imgElement.src = data.message;
            })
            .catch(error => {
                console.error("Error fetching dog image:", error);
            });
    });
}

getRandomDogImage();