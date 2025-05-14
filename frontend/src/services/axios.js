import axios from "axios";

const api = axios.create({
  baseURL: "http://191.252.60.171:8081", // URL base da API
  headers: {
    "Content-Type": "application/json"
  }
});


api.interceptors.request.use(config => {
    const token = localStorage.getItem("token");
    console.log(token)
    if (token) {
      config.headers.Authorization = `${token}`;
    }
    console.log(config.headers)
    return config;
  });
  
  api.interceptors.response.use(
    response => response,
    error => {
      if (error.response.status === 401) {
        console.error("Não autorizado! Faça login novamente.");
      }
      return Promise.reject(error);
    }
  );


export default api;