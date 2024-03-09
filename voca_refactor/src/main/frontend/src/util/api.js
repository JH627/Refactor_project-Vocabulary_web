import axios from "axios";

import { QueryClient } from "@tanstack/react-query";

export const queryClient = new QueryClient();

const BASE_URL = "http://localhost:8080";
// const BASE_URL = 'http://localhost:8080/api';

/**
 * axios create method error handling
 * 나중에 session방식으로 전환
 */

// Without Auth
const axiosAPI = (url, options) => {
  const instance = axios.create({ baseURL: url, ...options });
  // error handle code
  return instance;
};

// With Auth
const axiosAuthAPI = (url, options) => {
  const instance = axios.create({ baseURL: url, ...options });
  return instance;
};

export const defaultInstance = axiosAPI(BASE_URL);
export const authInstance = axiosAuthAPI(BASE_URL);

// request
authInstance.interceptors.request.use(function (config) {
  const accessToken = localStorage.getItem("token");
  config.headers["authorization"] = "Bearer " + accessToken;
  return config;
});
