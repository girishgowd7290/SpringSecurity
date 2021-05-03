# SpringSecurity


# Add Source Code to Repo

1. Create a new repo in Git
2. Terminal > Change the current working directory to your local project.
3. Initialize the local directory as a Git repository.

```bash
 $ git init -b main
```
4. Add the files in your new local repository. This stages them for the first commit.
```bash
 $ git add .
```
5. Commit the files that you've staged in your local repository.
```bash
$ git commit -m "First commit"
```   
 Commits the tracked changes and prepares them to be pushed to a remote repository. To remove this commit and modify the file, use 'git reset --soft HEAD~1' and commit and add the file again.

 6. Add remote repo url
```bash
$ git remote add origin  https://github.com/girishgowd7290/SpringSecurity.git
    # Sets the new remote
    $ git remote -v
    # Verifies the new remote URL 
```  
     
7. Push the changes in your local repository to GitHub.
``` bash
    $ git branch -M main 
    $ git push -u origin main
    # Pushes the changes in your local repository up to the remote repository you specified as the origin 
```  

## Github commands: 
```bash
echo "# SpringSecurity" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin https://github.com/girishgowd7290/SpringSecurity.git
git push -u origin main

```
## Reference
[Github](https://docs.github.com/en/github/importing-your-projects-to-github/adding-an-existing-project-to-github-using-the-command-line)
