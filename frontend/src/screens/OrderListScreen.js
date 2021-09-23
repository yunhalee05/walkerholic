import React from 'react'
import { useState } from 'react'
import { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { getOrderList, getOrderListBySeller } from '../_actions/OrderActions'

function OrderListScreen(props) {

    const id = props.match.params.id
    const [page, setPage] = useState(1)

    const auth = useSelector(state => state.auth)
    const list = useSelector(state => state.list)
    const {orders} = list

    const dispatch = useDispatch()

    const pages = [...Array(orders.totalPage).keys()]


    useEffect(() => {
        if(id){
            dispatch(getOrderListBySeller(page, id))
        }else{
            dispatch(getOrderList(page))
        }
    }, [dispatch, page])

    return (
        <div className="list">
            <div className="list_title">
                Orders .
            </div>

            <table>
                <thead>
                    <th>ID</th>
                    <th>User</th>
                    <th>Payment</th>
                    <th>Delivery</th>
                    <th>Status</th>
                    <th></th>
                </thead>
                {
                    orders &&
                    orders.orders.map((order,index)=>(
                        <tbody key={index}>
                            <td>{order.id}</td>
                            <td>
                                <div className="orderlist_user">
                                    <img src={order.user.imageUrl} alt="" />
                                    <span>{order.user.fullname}</span>
                                </div>
                            </td>
                            <td>{order.isPaid? order.paidAt : "Not yet"}</td>
                            <td>{order.isDelivered? order.deliveredAt : "Not yet"}</td>
                            <td>{order.orderStatus}</td>
                            <td>
                                <div className="orderlist_action">
                                    <i class="far fa-edit"></i>
                                    <i class="far fa-trash-alt"></i>
                                </div>
                            </td>
                        </tbody>
                    ))
                }
            </table>

            {
                // orders.totalPage > 1 &&
                <nav aria-label="Pagination">
                    <ul className="pagination">
                        <li className="page-item" style={{borderRadius:"10px 0px 0px 10px"}}>
                            <i className="fas fa-backward" onClick={()=>setPage(1)}></i>
                        </li>
                        {/* {
                            pages.map((x, index)=>(
                                <li className={`page-item ${page===x+1 && 'page_active'}`} onClick={()=>setPage(x+1)}>{x+1}</li>
                            ))
                        } */}
                        <li className={`page-item ${page===1 && 'page_active'}`}>1</li>
                        <li className="page-item">2</li>
                        <li className="page-item">3</li>
                        <li className="page-item" style={{borderRadius:"0px 10px 10px 0px"}}>
                            <i className="fas fa-forward"></i>
                        </li>
                    </ul>
                </nav>
            }
        </div>
    )
}

export default OrderListScreen
