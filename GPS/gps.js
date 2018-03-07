/* jslint browser:true */

var id = null;
var firstTime = -1;
var calibration1 = {lat: 43.7721276, lon: -79.5061094, x:369.56, y:456.52, desc:"SL"};
var calibration2 = {lat: 43.7738228, lon: -79.5052296, x:521.74, y:217.4, desc:"LAB"};
var loc1 = {lat: 43.7734508, lon: -79.507826, desc:"WSC"};
var loc2 = {lat: 43.7725078, lon: -79.5073434, desc:"BC"};
var loc3 = {lat: 43.775357, lon: -79.507424, desc:"LSB"};
var caches = new Array();
caches[0] = loc1;
caches[1] = loc2;
caches[2] = loc3;
var currentCaches = 0;

function togglegps() {
    var button = document.getElementById("togglegps");
    if (navigator.geolocation) {
        if (id === null) {
            id = navigator.geolocation.watchPosition(showPosition, handleError, {enableHighAccuracy : true, timeout: 1000});
            button.innerHTML = "STOP GPS";
            firstTime = -1;
        } else {
            navigator.geolocation.clearWatch(id);
            id = null;
            button.innerHTML = "START GPS";
        }
    } else {
        alert("NO GPS AVAILABLE");
    }
}

function handleError(error) {
    var errorstr = "Really unknown error";
    switch (error.code) {
    case error.PERMISSION_DENIED:
        errorstr = "Permission deined";
        break;
    case error.POSITION_UNAVAILABLE:
        errorstr = "Permission unavailable";
        break;
    case error.TIMEOUT:
        errorstr = "Timeout";
        break;
    case error.UNKNOWN_ERROR:
        error = "Unknown error";
        break;
    }
    alert("GPS error " + error);
}

function interpolate(gps1, gps2, u1, u2, gps)
{
  return (u1+(u2-u1)*(gps-gps1)/(gps2-gps1));
}


function showPosition(position) {
    var latitude = document.getElementById("latitude");
    var longitude = document.getElementById("longitude");
    var now = document.getElementById("now");
    var x = interpolate(calibration1.lon, calibration2.lon,calibration1.x, calibration2.x, position.coords.longitude);
    var y = interpolate(calibration1.lat, calibration2.lat,calibration1.y, calibration2.y, position.coords.latitude);
    document.getElementById("debug").innerHTML = "x: "+ x +" "+"y: "+ y;
    latitude.innerHTML = position.coords.latitude;
    longitude.innerHTML = position.coords.longitude;
    if(x > 1000)
    {
      x = 1000;
    }
    else if (x < 0)
    {
      x = 0;
    }
    if(y > 500)
    {
      y = 500;
    }
    else if(y < 0)
    {
      y = 0;
    }
    document.getElementById("me").style.left = x + "px";
    document.getElementById("me").style.top = y + "px";

    if (firstTime < 0) {
      firstTime = position.timestamp;
    }
    now.innerHTML = position.timestamp - firstTime;
}

function updateCache()
{
  if(currentCaches == 3)
  {
    currentCaches = 0;
  }
  showCache(caches);
  currentCaches ++;
}

function showCache()
{
  var b = interpolate(calibration1.lat, calibration2.lat,calibration1.y, calibration2.y, caches[currentCaches].lat);
  var a = interpolate(calibration1.lon, calibration2.lon,calibration1.x, calibration2.x, caches[currentCaches].lon);

  /*if(a > 1000)
  {
    a = 1000;
  }
  else if (a < 0)
  {
    a = 0;
  }
  if(b > 500)
  {
    b = 500;
  }
  else if(b < 0)
  {
    b = 0;
  }*/

  document.getElementById("target").style.left = a + "px";
  document.getElementById("target").style.top = b + "px";
 document.getElementById("debug2").innerHTML = "a: "+ a +" "+"b: "+ b;
}
