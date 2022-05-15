import axios from 'axios'
import React from 'react'

function PaypalTest() {

    const paypalTest = async() =>{
        await axios.post("/paypal/test", null).then(res=>{
            console.log(res)
            window.location.href = res.data
        })

    }


  return (
    
    <div>
        <div> PaypalTest</div>
        <button onClick={paypalTest}>onTest</button>
    </div>
    
  )
}

export default PaypalTest