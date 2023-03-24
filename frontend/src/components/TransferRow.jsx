import React from 'react'
import PropTypes from 'prop-types'

const TransferRow = props => {
  return (
    <tr class="bg-white border-b dark:bg-gray-900 dark:border-gray-700">
      <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
        {props.transfer.user.firstName + ' ' + props.transfer.user.lastName}
      </th>
      <td class="px-6 py-4">
        {props.transfer.user.contactNo}
      </td>
      <td class="px-6 py-4">
        {props.transfer.class}
      </td>
      <td class="px-6 py-4">
        {props.transfer.stream}
      </td>
      <td class="px-6 py-4">
        <a href="#" onClick={props.handleAccept} value={props.transfer.id} class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Accept</a>
        &nbsp;
        <a href="#" onClick={props.handleReject} value={props.transfer.id} class="font-medium text-blue-600 dark:text-blue-500 hover:underline">Reject</a>
      </td>
    </tr>
  )
}

TransferRow.propTypes = {}

export default TransferRow