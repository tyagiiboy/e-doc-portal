import axios from "axios";
import { REGISTER_SCHOOL_URL } from "../commons/constants";

class SchoolService {
  saveSchool(school) {
    return axios.post(REGISTER_SCHOOL_URL, school);
  }
}
const schoolService = new SchoolService();
export default schoolService;