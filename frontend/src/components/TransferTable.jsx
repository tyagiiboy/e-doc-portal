import { useEffect, useState } from "react"
import TransferRow from "./TransferRow"
import transferService from '../service/transfer.service'
import { useSelector } from "react-redux"

const TransferTable = (props) => {
  const user = useSelector(state => state.user)
  const [transfers, setTransfers] = useState([])

  const handleAccept = (event) => {
    let id = event.target.value
    transferService.acceptTransferForSchool(user.id, [id])
    setTransfers([])
  }

  const handleReject = (event) => {
    let id = event.target.id
    transferService.rejectTransferForSchool(user.id, [id])
    setTransfers([])
  }

  useEffect(() => {
    transferService.getTransfersForSchool(user.id).then(
      response => setTransfers(response.data)
    )
  }, [transfers])

  return (
    <div class='p-3'>

      <div class="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" class="px-6 py-3">
                Name
              </th>
              <th scope="col" class="px-6 py-3">
                Contact
              </th>
              <th scope="col" class="px-6 py-3">
                Class
              </th>
              <th scope="col" class="px-6 py-3">
                Stream
              </th>
              <th scope="col" class="px-6 py-3">

              </th>
            </tr>
          </thead>
          <tbody>
            {
              transfers.map(
                transfer => (
                  <TransferRow key={transfer.id}
                    transfer={transfer}
                    handleAccept={handleAccept}
                    handleReject={handleReject}
                  />
                )
              )
            }
          </tbody>

        </table>
      </div>

      <p class='text-center text-lg my-2'>
        {transfers.length ? '' : 'No Transfer Requests yet'}
      </p>

    </div>
  )
}

export default TransferTable