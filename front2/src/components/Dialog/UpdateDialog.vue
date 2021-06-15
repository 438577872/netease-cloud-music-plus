<template>
  <el-dialog :visible="visible.update">
    <template slot="title">
      上传歌曲
    </template>

    <template>
      <el-form label-width="100px">
        <el-form-item label="ID">
          <el-input v-model="music.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="歌曲名称">
          <el-input v-model="music.musicName"/>
        </el-form-item>

        <el-form-item label="歌手姓名">
          <el-input v-model="music.author"/>
        </el-form-item>

        <el-form-item label="上传封面">
          <input type="file" @change="handleChangeImg"></input>
        </el-form-item>

        <el-form-item label="上传歌曲">
          <input style="opacity: 1" @change="handleChangeMusic" type="file"/>
        </el-form-item>

        <el-form-item label="">
          <el-button type="primary" @click="handleCommit">
            上传
          </el-button>
        </el-form-item>

      </el-form>
    </template>


    <template slot="footer">
      <el-button type="danger" @click="visible.upload = false">
        关闭
      </el-button>
    </template>
  </el-dialog>
</template>

<script>
  import { updateMusic, uploadMusic } from '../../api/music'

  export default {
    name: 'UpdateDialog',
    props: ['visible', 'music'],
    data() {
      let form = {
        id: '',
        musicName: '',
        author: '',
        musicFile: null,
        imgFile: null
      }
      return {
        form
      }
    },
    methods: {
      handleChangeImg(e) {
        this.form.imgFile = e.target.files[0]

      },
      handleChangeMusic(e) {
        this.form.musicFile = e.target.files[0]
        // this.form.musicName = musicName
      },
      handleCommit() {
        let fd = new FormData()
        for (let key in this.form) {
          fd.append(key, this.form[key])
        }
        for (let key in this.music) {
          fd.append(key, this.music[key])
        }

        updateMusic(fd).then(resp => {


        })

        // fd.append('')
      }
    }
  }
</script>

<style scoped>

</style>
