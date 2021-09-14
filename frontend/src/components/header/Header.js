import React from 'react'
import { useState } from 'react'
import { useSelector } from 'react-redux'
import {Link} from 'react-router-dom'

function Header() {

    const [keyword, setKeyword] = useState('')

    const auth = useSelector(state => state.auth)

    return (
        <div className="header">
            <div className="header_logo">
                <i class="fas fa-walking"></i>
                <i class="fas fa-male"></i>
                <Link to='/'>walkerholic</Link>
            </div>

            <div className="header_search">
                <input type="text" value={keyword} onChange={e=>setKeyword(e.target.value)} />
                <i class="fas fa-eraser" style={keyword.length>0 ? {transform:'translateX(-20px)'}: {color:'white'}}></i>
                <i class="far fa-search"></i>
            </div>

            <div className="header_menu">
                {
                    auth.user &&
                    <div className="header_content">
                        <Link to={`/posts/${auth.user.id}`}>Posts</Link>
                    </div>

                }

                <div className="header_content">
                    <Link to="/posts">Discover</Link>
                </div>



                {
                    auth.user 
                    ?<div className="header_content">
                        {/* <Link to='/user/1'>{auth.user.firstname}</Link> */}

                        <div className="dropdown dropdown-toggle">
                            <span>{auth.user.firstname}</span>
                            <div className="dropdown-menu dropdown-menu-right" >
                                <Link className="dropdown-item" to={`/user/${auth.user.id}`}>Profile</Link>
                                <Link className="dropdown-item" to={`/user/activity/${auth.user.id}`}>My Activities</Link>
                                {
                                    auth.user.role==="SELLER" &&
                                    <Link className="dropdown-item" to={`/user/product/${auth.user.id}`} >Products</Link>
                                }
                            </div>
                        </div>
                    </div>
                    :<div className="header_content">
                            <Link to='/signin'>Login</Link>
                    </div>
                }
            </div>
        </div>
    )
}

export default Header
