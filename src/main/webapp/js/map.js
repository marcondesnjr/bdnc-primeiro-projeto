var map;
var heatmap
function initialize() {



    // Exibir mapa;
    var myLatlng = new google.maps.LatLng(-8.0631495, -34.87131120000004);
    var mapOptions = {
        zoom: 17,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };


    marker = new google.maps.Marker({
        map: map,
        draggable: true,
    });

    map = new google.maps.Map(document.getElementById("mapa"), mapOptions);

    heatmap = new google.maps.visualization.HeatmapLayer({
        data: points,
        map: map
    });

    google.maps.event.addListener(map, 'click', function (event) {
        addIncidente(event.latLng);
    });



    function addIncidente(location) {
//        marker = new google.maps.Marker({
//            position: location,
//            map: map,
//            title: 'Novo Marcador',
//            animation: google.maps.Animation.DROP
//        });
//        map.setCenter(location);
        document.getElementById("localizacao").value = location;
        PF('addIncidenteDialog').show();
    }

    var infoWindow = new google.maps.InfoWindow({map: map});

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('Você está aqui!');
            map.setCenter(pos);
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }


}

function geocodeAddress() {
    var geocoder = new google.maps.Geocoder();
    var resultsMap = map;
    var address = document.getElementById('address').value;
    geocoder.geocode({'address': address}, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            resultsMap.setCenter(results[0].geometry.location);
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}

function toggleHeatmap() {
    heatmap.setMap(heatmap.getMap() ? null : map);
}

function changeRadius() {
    heatmap.set('radius', heatmap.get('radius') ? null : 20);
}

function changeOpacity() {
    heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);
}

function hideMarkers(markers) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setVisible(!markers[i].getVisible());
    }
}

function setMapOnAll(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function clearMarkers() {
    setMapOnAll(null);
}

function initMap(){
    document.getElementById("hidden:load").click();
    alert("foi");
}


// Função para carregamento assíncrono
function loadScript() {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "http://maps.googleapis.com/maps/api/js?key=AIzaSyDeHb17So0QupSGO_d6b8X-OyvJ32UQehs&sensor=true&callback=initialize&libraries=visualization";
    document.body.appendChild(script);
}

window.onload = loadScript;
