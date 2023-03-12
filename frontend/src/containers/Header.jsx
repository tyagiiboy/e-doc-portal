import React from 'react'
import { useState } from 'react'

const Header = () => {

  const [dropDownState, setdropDownState] = useState(false)

  const onClickHandle = () => {
    setdropDownState((dropDownState) => !dropDownState)
  }

  return (
    <header className='bg-teal-700 h-12 sticky top-0 z-20 p-1'>
      <section className='max-w-4xl  mx-auto flex justify-between'>
        <h1 className='text-white text-3xl'>📜 E-DOC</h1>
        <nav className="">
          <button id="dropdownDefaultButton" className="text-white bg-teal-700 hover:bg-teal-800 font-medium rounded-lg text-sm px-4 py-2.5 text-center inline-flex items-center" onClick={onClickHandle} type="button">Dropdown options<svg className="w-4 h-4 ml-2" aria-hidden="true" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7"></path></svg></button>
          <div id="dropdown" className={`z-20 ${dropDownState? '': 'hidden'} bg-teal-700 divide-y divide-gray-100 rounded-lg shadow w-44 mt-2`}>
            <ul class="py-2 text-sm text-white dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
              <li>
                <a href="#" className="block px-4 py-2 hover:bg-teal-300 hover:text-black dark:hover:bg-teal-500 dark:hover:text-black">Events</a>
              </li>
              <li>
                <a href="#" className="block px-4 py-2 hover:bg-teal-300 hover:text-black dark:hover:bg-teal-500 dark:hover:text-black">Search</a>
              </li>
              <li>
                <a href="#" className="block px-4 py-2 hover:bg-teal-300 hover:text-black dark:hover:bg-teal-500 dark:hover:text-black">Profile</a>
              </li>
              <li>
                <a href="#" className="block px-4 py-2 hover:bg-teal-300 hover:text-black dark:hover:bg-teal-500 dark:hover:text-black">Sign out</a>
              </li>
            </ul>
          </div>
        </nav>
      </section>
    </header>
  )
}

export default Header