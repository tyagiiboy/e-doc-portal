import { SET_CURRENT_USER, CLEAR_CURRENT_USER } from "../type";

const userReducer = (state = {}, action) => {
  switch (action?.type) {
    case SET_CURRENT_USER:
      console.log('set curnt user ' + JSON.stringify(action?.payload));
      localStorage.setItem('currentUser', JSON.stringify(action?.payload));
      return action?.payload;
    case CLEAR_CURRENT_USER:
      localStorage.removeItem('currentUser');
      return null;
    default:
      return JSON.parse(localStorage.getItem('currentUser'));
  }
};

export default userReducer;
