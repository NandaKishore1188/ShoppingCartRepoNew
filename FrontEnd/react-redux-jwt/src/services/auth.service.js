import axios from "axios";

const API_URL = "http://localhost:9091/";

const API_URL1 = "http://localhost:9091/customers/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "authenticate", { username, password })
      .then((response) => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(fullName, emailId, password,mobileNumber,gender) {
    return axios.post(API_URL1 + "save", {
      fullName,
      emailId,
      password,
      mobileNumber,
      gender
    });
  }
}

export default new AuthService();
