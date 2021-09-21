import React from 'react'
import { useSelector } from 'react-redux'
import {Link} from 'react-router-dom'
import { auth } from '../../_actions/AuthActions'

function Sidebar({isOpen,setIsOpen}) {

    const auth = useSelector(state => state.auth)
    return (
        <div className={isOpen? "sidebar sideopen": "sidebar sideclose"}>
            <div className="sidebar_container">
                <div className="sidebar_logo">
                    <div className="header_logo">
                        <i class="fas fa-bars" style={{marginRight:'1rem'}}></i>
                        <i class="fas fa-male" ></i>
                        <Link to='/'>walkerholic</Link>
                    </div>
                    <div style={{fontSize:'3rem', fontWeight:'800', cursor:'pointer'}} onClick={()=>setIsOpen(false)}>
                        &times;
                    </div>
                </div>
                <div className="sidebar_items">
                    <div className="sidebar_item">
                        <div className="sidebar_item_name">
                            <Link to={auth.user?.id ? `/posts/${auth.user.id}`:'/posts'}>Post</Link>
                        </div>
                        <div className="sidebar_description">
                            Checkout our planetsaver's latest moment
                        </div>
                    </div>
                    <div className="sidebar_item">
                        <div className="sidebar_item_name">
                            <Link to="/products">Product</Link>
                        </div>
                        <div className="sidebar_description">
                            Explore ecofriendly products
                        </div>
                    </div>
                    <div className="sidebar_item">
                        <div className="sidebar_item_name">
                            <Link to="/activities">Activity</Link>
                        </div> 
                        <div className="sidebar_description">
                            Help planet's health with your power
                        </div>
                    </div>
                    <div className="sidebar_item">
                        <div className="sidebar_item_name">
                            <Link>About</Link>
                        </div>
                        <div className="sidebar_description">
                            Our vision
                        </div>
                    </div>

                </div>
            </div>
        </div>
    )
}

export default Sidebar
