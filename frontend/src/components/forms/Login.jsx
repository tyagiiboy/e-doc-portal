import React from 'react'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux';
import authenticationService from '../../service/authentication.service';
import { setCurrentUser } from '../../store/actions/user';
import AuthRequest from '../../model/AuthRequest';


const Login = (props) => {

  const [user, setUser] = useState(new AuthRequest());
  const [loading, setLoading] = useState(false);
  const [submitted, setSubmitted] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const currentUser = useSelector(state => state.user);

  const navigate = useNavigate();

  const dispatch = useDispatch();

  const handleChange = (e) => {
    const { name, value } = e.target;

    setUser((prevState => {
      return {
        ...prevState,
        [name]: value
      };
    }));
  };

  const submitHandler = (e) => {
    e.preventDefault();

    setSubmitted(true);

    if (!user.username || !user.password) {
      return;
    }

    setLoading(true);
    console.log("email " + user.username + " pwd " + user.password);
    authenticationService.login(user).then(response => {
      console.log("login success " + response.data.username)

      dispatch(setCurrentUser(response.data));
      navigate('/');
    }).catch(error => {
      console.log(error);
      setErrorMessage('email or password is not valid.');
      setLoading(false);
    });
  };

  return (
    <div className='p-20'>

      <form onSubmit={submitHandler}>

        <p className='dark:text-white text-3xl mb-11'>Login</p>

        <div class="relative z-0 w-full mb-6 group">
          <input onChange={handleChange} type="text" name="username" id="floating_username" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
          <label for="floating_username" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Username</label>
        </div>

        <div class="relative z-0 w-full mb-6 group">
          <input onChange={handleChange} type="password" name="password" id="floating_password" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-white dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " required />
          <label for="floating_password" class="peer-focus:font-medium absolute text-sm text-gray-500 dark:text-gray-400 duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">Password</label>
        </div>

        <div class="flex justify-between">
          <button type="submit" className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" >Login</button>

          {/* <button type="button" className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" onClick={() => { navigate('/register') }}>Sign Up</button>  */}
          <p>
            New User? &nbsp;
            <a href="/registerUser" class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Sign up</a>
            &nbsp; here.
          </p>
        </div>


      </form>

      <div class="relative z-0 w-full group mt-10">
        <p class="text-gray-500 dark:text-gray-400">Lets us simplify the hardwork, register your <a href="/registerSchool" class="inline-flex items-center font-medium text-blue-600 dark:text-blue-500 hover:underline">
          Organization
          <svg aria-hidden="true" class="w-5 h-5 ml-1" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M12.293 5.293a1 1 0 011.414 0l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414-1.414L14.586 11H3a1 1 0 110-2h11.586l-2.293-2.293a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
        </a></p>
      </div>

    </div>
  )
}

export default Login