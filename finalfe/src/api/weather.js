import axios from "axios";

function getWeatherApi(loc) {
  const BASE_URL = `https://api.openweathermap.org/data/2.5/weather?lat=${loc.lat}&lon=${loc.lon}&appid=${process.env.VUE_APP_WEATHER_KEY}&units=metric`;
  console.log("여기 weather.js", BASE_URL);
  return axios.get(BASE_URL);
}

export { getWeatherApi };
