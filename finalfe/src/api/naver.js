import { apiInstance } from "./index.js";

const api = apiInstance();
function getImage(keyword, success, fail) {
  console.log("g", keyword);
  api.get(`/naver/image/${keyword}`).then(success).catch(fail);
}
//   fetch("http://localhost:7777/rest/naver/blog?keyword=" + keyword)
//     .then((resp) => resp.json())
//     .then((data) => {
//       console.log(data);
//       let el = ``;
//       for (let i = 0; i < data.items.length; i++) {
//         el += `<h3>${data.items[i].title}</h3>`;
//         el += `${data.items[i].bloggername}`;
//         el += `<hr>`;
//       }
//       document.getElementById("result").innerHTML = el;
//     });
export { getImage };
