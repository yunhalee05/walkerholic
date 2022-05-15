import axios from 'axios'
import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'

function PaypalReviewTest(props) {

    const payerId = new URLSearchParams(props.location.search).get("PayerID")
    const paymentId = new URLSearchParams(props.location.search).get("paymentId")
    const [total, setTotal] = useState(0)
    const [saleId, setSaleId] = useState("")

    console.log(payerId)
    console.log(paymentId)

    useEffect(() => {
      if(total === 0){
        getPaymentDetail()
      }
    }, [total])

    const getPaymentDetail = async() =>{
        await axios.get(`/paypal/detail/test?paymentId=${paymentId}&payerId=${payerId}`).then(res=>{
            console.log(res)
            setTotal(1)
        })
    }
    const paymentPaypal = async() =>{
        await axios.post(`/paypal/payment?paymentId=${paymentId}&payerId=${payerId}`).then(res=>{
            console.log(res)
            setSaleId(res.data)
        })
    }

    const paymentRefund = async() =>{
        await axios.post(`/paypal/refund?saleId=${saleId}`).then(res=>{
            console.log(res)
        })
    }
    
    return (
    <div>PaypalReviewTest
        <button onClick={getPaymentDetail}>button</button>
        <button onClick={paymentPaypal}>button</button>
        <button onClick={paymentRefund}>button</button>
    </div>
  )
}

export default PaypalReviewTest