import axios from "axios";
import { REGISTER_USER_URL } from "../commons/constants";

class UserService {
  saveUser(user) {
    return axios.post(REGISTER_USER_URL, user);
  }
}

export default new UserService();