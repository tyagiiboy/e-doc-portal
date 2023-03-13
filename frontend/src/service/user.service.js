import axios from "axios";
import { REGISTER_USER_URL,USER_PROFILE_URL } from "../commons/constants";
import { authHeader } from "./base.service";

class UserService {
  saveUser(user) {
    return  axios.post(REGISTER_USER_URL, user);
  }

  getprofile(userid){
    return axios.get(USER_PROFILE_URL + userid, {headers:authHeader()});
  }

}
const userService = new UserService();

export default userService;