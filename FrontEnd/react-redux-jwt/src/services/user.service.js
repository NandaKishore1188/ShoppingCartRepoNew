import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:9091/";

class UserService {
  getPublicContent() {
    return axios.get(API_URL + "hello");
  }

  getGreetings() {
    return axios.get(API_URL + "hello", { headers: authHeader() });
  }
  
}

export default new UserService();
