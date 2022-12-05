import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:9999/finalbe/",
  headers: {
    "Content-type": "application.json",
  },
});
