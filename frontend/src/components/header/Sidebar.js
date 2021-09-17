import React from 'react'
import { useSelector } from 'react-redux'
import {Link} from 'react-router-dom'
import { auth } from '../../_actions/AuthActions'

function Sidebar() {

    const auth = useSelector(state => state.auth)
    return (
        <div className="sidebar">
            <div className="sidebar_container">
                <div className="sidebar_items">
                    <div>
                        <span>01</span>
                        {/* <Link to={auth.user.id ? `/posts/${auth.user.id}`:'/posts'}>Post</Link> */}
                    </div>
                    <div>
                        <span>02</span>
                        <Link>Product</Link>
                    </div>
                    <div>
                          &nbsp;
                    </div>
                    <div>
                        <span>03</span>
                        <Link>Activity</Link>
                    </div>
                    <div>
                        <span>04</span>
                        <Link>About</Link>
                    </div>
                    <div>
                        &times;
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Sidebar
