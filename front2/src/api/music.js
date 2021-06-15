import request from '@/utils/request'

export function getMusic(params) {
  return request({
    url: '/music/get',
    method: 'get',
    params

  })
}

export function deleteMusic(id) {
  return request({
    url: '/music/delete',
    method: 'delete',
    params: { id }
  })
}



export function uploadMusic(formData) {
  return request({
    url: '/music/upload',
    method: 'post',
    data: formData
  })
}

export function updateMusic(formData) {
  return request({
    url: '/music/update',
    method: 'post',
    data: formData
  })
}
