import React from 'react'

const Footer = () => {
  return (
    <div class="bg-white dark:bg-gray-800 mt-3">

      <footer class="max-w-4xl md:flex md:items-center md:justify-between md:p-6 rounded-lg mx-auto flex justify-between">
        <span class="text-sm text-gray-500 sm:text-center dark:text-gray-400">© 2023 <a href="https://flowbite.com/" class="hover:underline">E-DOC</a>. All Rights Reserved.
        </span>
        <ul class="flex flex-wrap items-center mt-3 text-sm text-gray-500 dark:text-gray-400 sm:mt-0">
          <li>
            <a href="#" class="mr-4 hover:underline md:mr-6 ">About</a>
          </li>
          <li>
            <a href="#" class="mr-4 hover:underline md:mr-6">Privacy Policy</a>
          </li>
          <li>
            <a href="/school" class="mr-4 hover:underline md:mr-6">Register Organization</a>
          </li>
          <li>
            <a href="#" class="hover:underline">Contact</a>
          </li>
        </ul>
      </footer>

    </div>
  )
}

export default Footer