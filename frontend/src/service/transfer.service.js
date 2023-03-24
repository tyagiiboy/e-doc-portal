import axios from "axios";
import { ACCEPT_TRANSFERS_URL, REJECT_TRANSFERS_URL, TRANSFERS_URL } from "../commons/constants";
import { authHeader } from "./base.service";


class TransferService {
  
  getTransfersForSchool(diseCode) {
    return axios.get(TRANSFERS_URL + diseCode, {
      headers: authHeader()
    })
  }

  acceptTransferForSchool(diseCode, arr) {
    return axios.post(ACCEPT_TRANSFERS_URL + diseCode), arr, {
      headers: authHeader()
    }
  }

  rejectTransferForSchool(diseCode, arr) {
    return axios.post(REJECT_TRANSFERS_URL + diseCode), arr, {
      headers: authHeader()
    }
  }

}

const transferService = new TransferService();
export default transferService;