import axios from 'axios';
import React from 'react'

// function Test() {

// const token = `AdloUvXe4gXo0aniL-xP-cMBM7gggscrSbYrqXdKaOdpnjB4cpe_e4vxgNlOB5bOSjdWtlT7Hqp0NmaK:EG9kiSubPVMSCzgUlAU0VNB8OEv5x4t9_27VKGtkQeoxXwNKjoi2vAET9BhQktNwcuTEKFcnvWOSUQSo`;
// const encodedToken = Buffer.from(token).toString('base64');

// const handleButton = (e) =>{
//     e.preventDefault()

//     const res = axios.get(`https://api-m.sandbox.paypal.com/v2/checkout/orders/4CY130973R530225V`,{
//         headers : {
//         Authorization: "Basic " + encodedToken,
//         'Content-type':'application/json'
//     }})

//     console.log(res.data)
// }

//   return (
//     <div>
//         <button onClick={handleButton}>button</button>
//     </div>
//   )
// }

// export default Test


function Test() {


    const handleCheckAddress=async(e)=>{
        e.preventDefault()

        const token = `AdloUvXe4gXo0aniL-xP-cMBM7gggscrSbYrqXdKaOdpnjB4cpe_e4vxgNlOB5bOSjdWtlT7Hqp0NmaK:EG9kiSubPVMSCzgUlAU0VNB8OEv5x4t9_27VKGtkQeoxXwNKjoi2vAET9BhQktNwcuTEKFcnvWOSUQSo`;
        const encodedToken = btoa(token)

        const res = await axios.get(`https://api-m.sandbox.paypal.com/v2/checkout/orders/4CY130973R530225V`,{
            headers : {
            Authorization: "Basic " + encodedToken,
            'Content-type':'application/json'
        }})

        console.log(res.data)
    }

    return (
        <>
       
        {
            <div className="placeorder">
                <div className="placeorder_info">
                        <form onSubmit={handleCheckAddress}>
                            <div className="placeorder_address">
                                <div className="placeorder_subtitle">🏠 Shipping Address </div>
                                <div style={{textAlign:"right"}}>
                                    <button>Check Address</button>                                    
                                </div>
                            
                            </div>
                        </form>
                        
                </div>
            </div>
        }
        </>
    )
}

export default Test
