function setText() {
    document.getElementById("woedfromplace").innerHTML = "New text!";
    };
var x = document.getElementById("woedfromplace");

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    x.innerHTML = "Latitude: " + position + 
    "<br>Longitude: " + position.coords.longitude;
}

setText();