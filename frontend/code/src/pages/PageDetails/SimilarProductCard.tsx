import React from "react";

const SimilarProductCard = () => {
    return (
        <div>
            <div className='group px-4 relative'>
                <div className="card">
                    <img className="card-media object-top" src="https://media.istockphoto.com/id/1354020636/tr/fotoğraf/black-t-shirt-mockup-front-used-as-design-template-tee-shirt-blank-isolated-on-white.jpg?s=2048x2048&w=is&k=20&c=afjaZvVS8-kbBsbj7oMGUTF0Ww6ujL1mMPF2skyct1Q=" alt=""
                    />


                </div>
                <div className='details pt-3 space-y-1 group-hover-effect rounded-md'>
                    <div className="name">
                        <h1>H&M</h1>
                        <p>White Tshirt</p>
                    </div>
                    <div className='price flex items-center gap-3'>
                        <span className="font-sans •text-gray-800">
                            400
                        </span>
                        <span className="thin-line-trhough text-gray-400">
                            <s>999</s>
                        </span>
                        <span className="text-primary-color font-semibold">
                            %60
                        </span>


                    </div>
                </div>

            </div>
        </div>
    )
}

export default SimilarProductCard