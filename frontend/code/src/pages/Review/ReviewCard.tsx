import { Delete } from "@mui/icons-material";
import { Avatar, Box, Grid, Grid2, IconButton, Rating } from "@mui/material";
import { red } from "@mui/material/colors";
import React from "react";

const ReviewCard = () => {
    return (
        <div className='flex justify-between'>
            <Grid2 container spacing={8} >
                <Grid2 size={{ xs: 1 }}>
                    <Box>
                        <Avatar className='text-white' sx={{ width: 56, height: 56, bgcolor: "#9155FD" }}>
                            M
                        </Avatar>
                    </Box>
                </Grid2>
                <Grid2 size={{ xs: 9 }}>
                    <div className='space-y-2'>
                        <div>
                            <p className='font-semibold text-lg'>MuratErtik</p>
                            <p className='opacity-70' >2025-02-10: 16:07</p>
                        </div>
                    </div>
                    <Rating readOnly value={4} precision={1} />
                    <p>valuable for spend money.</p>
                    <div>
                        <img className='w-24 h-24 object-cover' src="https://images.pexels.com/photos/9558786/pexels-photo-9558786.jpeg?auto=compress&cs=tinysrgb&w=1200" alt="" />
                    </div>

                </Grid2>

            </Grid2>
            <div>
                <IconButton>
                    <Delete sx={{ color: red[700] }} />
                </IconButton>
            </div>

        </div>
    )
}

export default ReviewCard