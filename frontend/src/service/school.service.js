import axios from "axios";
import { REGISTER_SCHOOL_URL , SCHOOL_LIST_URL, SCHOOL_URL} from "../commons/constants";
import { authHeader } from "./base.service";

class SchoolService {
  saveSchool(school) {
    return axios.post(REGISTER_SCHOOL_URL, school);
  }

  getschools(){
    return axios.get(SCHOOL_LIST_URL , {headers:authHeader()});
  }

  getSchool(diseCode) {
    return axios.get(SCHOOL_URL + diseCode, {headers: authHeader()});
  }

}
const schoolService = new SchoolService();
export default schoolService;