import React, { useRef, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { checkPostImage } from '../../utils/CheckImage'
import { imageShow, videoShow} from '../../utils/MediaShow'
import { createPost } from '../../_actions/PostActions'


function EditPost({post, setIsCreate, setIsEdit, isCreate}) {

    const [content, setContent] = useState(post? post.content : '')
    const [images, setImages] = useState([])

    const [stream, setStream] = useState(false)
    const [tracks, setTracks] = useState('')

    const videoRef = useRef()
    const canvasRef = useRef()

    const auth = useSelector(state => state.auth)

    const dispatch = useDispatch()

    const handleChangeImages=(e)=>{
        const files = [...e.target.files]
        let newImages= []
        files.forEach(file=>{
            const err = checkPostImage(file)
            if(err) return window.alert(err)
            newImages.push(file)
        })

        setImages([...images, ...newImages])
    }

    const deleteImage=(index)=>{
        const newArr = [...images]
        newArr.splice(index, 1)
        setImages(newArr)
    }


    const handleStream = () =>{
        setStream(true)
        if(navigator.mediaDevices && navigator.mediaDevices.getUserMedia){
            navigator.mediaDevices.getUserMedia({video:true})
                                    .then(mediastream=>{
                                        videoRef.current.srcObject=mediastream
                                        videoRef.current.play()
                                        const track = mediastream.getTracks()
                                        setTracks(track[0])
                                    }).catch(err=>console.log(err))
        }
    }

    const handleCapture = () =>{
        
        const width = videoRef.current.clientWidth;
        const height = videoRef.current.clientHeight;

        canvasRef.current.setAttribute("width", width)
        canvasRef.current.setAttribute("height", height)

        const ctx = canvasRef.current.getContext('2d')
        ctx.drawImage(videoRef.current, 0, 0, width, height)
        let URL = canvasRef.current.toDataURL()

        var blobBin = atob(URL.split(',')[1]);	// base64 데이터 디코딩
        var array = [];
        for (var i = 0; i < blobBin.length; i++) {
            array.push(blobBin.charCodeAt(i));
        }
        var file = new Blob([new Uint8Array(array)], {type: 'image/png'});
        setImages([...images, file])  
    }

    const handleStopStream = () =>{
        tracks.stop()
        setStream(false)
    }

    const handleSubmit = (e) =>{
        e.preventDefault();

        if(images.length===0){
            return window.alert("Please add your photo.")
        }
        const bodyFormData = new FormData()


        if(post){
            bodyFormData.append("id", post.id)
            bodyFormData.append("content", content)
            bodyFormData.append("userId", auth.user.id)
            images.forEach(image=> bodyFormData.append("multipartFile", image))
        }else{
            bodyFormData.append("content", content)
            bodyFormData.append("userId", auth.user.id)
            images.forEach(image=> bodyFormData.append("multipartFile", image))
        }

        dispatch(createPost(bodyFormData))

    }


    return (
        <div className="edit_post" >

            <form onSubmit={handleSubmit} encType="multipart/form-data">
                {
                    post 
                    ? <div className="edit_title">Post {post.id}</div>
                    : <div className="edit_title">Create Post</div>
                }
                <div className="form_group">
                    <label htmlFor="content">Content</label>
                    <textarea type="text" className="form_control" value={content} onChange={e=>setContent(e.target.value)} />
                </div>

                <div className="show_images">
                    {

                        images && 
                        images.map((image, index)=>
                            <div key={index} id="file_img">
                                {
                                    // image.camera
                                    // ? imageShow(image.camera)
                                    // : 
                                    image.data
                                        ? <div>
                                            {
                                                image.data.match(/video/i)
                                                ? videoShow(image.data)
                                                : imageShow(image.data)
                                            }
                                        </div>
                                        : <div>
                                            {
                                                image.type.match(/video/i)
                                                ? videoShow(URL.createObjectURL(image))
                                                : imageShow(URL.createObjectURL(image))
                                            }
                                        </div>
                                }
                                <span onClick={()=>deleteImage(index)}>&times;</span>
                            </div>
                        )
                    }
                </div>

                {
                    stream &&
                    <div className="stream position-relative">
                        <video autoPlay muted ref={videoRef} width='100%' height='100%'></video>
                        <span onClick={handleStopStream}>&times;</span>
                        <canvas ref={canvasRef} style={{display:'none'}}></canvas>
                    </div>
                }
                <div className="input_images">
                    {
                        stream 
                        ? <i className="fas fa-camera" onClick={handleCapture}></i>
                        : <>
                            <div><i className="fas fa-camera" onClick={handleStream}></i></div>
                            <div className="file_upload">
                                <i className="fas fa-image"></i>
                                <input type="file" name="file" id="file_up" multiple accept="image/*, video/*" onChange={handleChangeImages}/>
                            </div>
                        </>
                    }
                </div>



                <div className="form_button">
                    <button type="submit" className="follow_button" style={{marginRight:"1rem"}}>Post</button>
                    <button onClick={()=>isCreate? setIsCreate(false):setIsEdit(false)} className="unfollow_button">Cancel</button>
                </div>
            </form>
        </div>
    )
}

export default EditPost
